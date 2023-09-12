<template>
    <el-upload
        ref="upload"
        class="upload-demo"
        drag
        :action="action"
        :limit="1"
        :on-exceed="handleExceed"
        :auto-upload="true"
        :on-change="hadFile"
        :before-upload="beforeImageUpload"
        :http-request="request">
        <el-icon class="el-icon--upload"></el-icon>
        <div class="el-upload__text">
            拖拽图片到这，<em>也可以点击打开文件夹</em>
        </div>
    </el-upload>
</template>

<script setup lang="ts">
    import axios from 'axios'
    import { storeToRefs } from "pinia";
    import { ElMessage, genFileId} from 'element-plus'
    import { ref, defineProps, defineEmits, defineExpose } from 'vue'
    import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'

    const props = defineProps({action:String, title:String, describe:String, money:String, tag:String, calendar:Date});
    const emits = defineEmits(["submitImageSuccess"]);

    const upload = ref<UploadInstance>()

    const handleExceed: UploadProps['onExceed'] = (files) => {
        upload.value!.clearFiles()
        const file = files[0] as UploadRawFile
        file.uid = genFileId()
        upload.value!.handleStart(file)
    }

    const isHadFile = ref(false);//判断是否有图片的变量
    const hadFile = () =>{
        isHadFile.value=true;
    }

    const beforeImageUpload: UploadProps['beforeUpload'] = (rawFile) => {//检查上传的文件是否为图片
        let typeArr=['image/jpeg', 'image/webp', 'image/png', 'svg+xml'];//能接收的图片文件类型
        if (typeArr.indexOf(rawFile.type)<0) {
        ElMessage.error('请输入图片类型文件')
        return false
        } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('图片大小不能超过2MB')
        return false
        }
        return true
    }

    async function submitUpload(){//提交方法
        if(isHadFile.value){
            upload.value!.submit();
        }
    }

    async function request(params:any){//替换掉原本的xhr请求
        const formData = new FormData();//第一次请求
        formData.append("file", params.file);
        formData.append("format", "jpg");
        let result = await axios({
            method: 'post',
            url: "api/user/setProfile",
            headers: {
                "Content-Type": "multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN",
            },
            data: formData,
        });
        console.log(result.data);
        if(!result.data.error){
            emits("submitImageSuccess");
            ElMessage.success("上传成功");
        }
        else{
            ElMessage.error("上传失败");
        }
    }

    defineExpose({//暴露子组件方法和属性给ref
        submitUpload,//暴露提交方法
        isHadFile//暴露判断是否有图片的变量
    })
</script>
  

  