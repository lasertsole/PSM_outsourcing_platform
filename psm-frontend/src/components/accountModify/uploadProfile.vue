<template>
    <el-upload
        class="avatar-uploader"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
        :http-request="request"
    >
        <img :src="imageUrl" class="avatar"/>
        <div class="mock">
            <div class="rowLine"></div>
            <div class="columnLine"></div>
        </div>
    </el-upload>
</template>
  
<script lang="ts" setup>
    import useGlobal from "global";
    import { ref, defineProps } from 'vue'
    import { ElMessage } from 'element-plus'
    import type { UploadProps, UploadRawFile} from 'element-plus'
    const global = useGlobal();
    const accountInfo = global?.accountInfo;//获取用户账号信息的pinia

    const props = defineProps({
        profile:{type:String, required: true}
    });
    const imageUrl = ref(props.profile);

    const handleAvatarSuccess: UploadProps['onSuccess'] = (response,uploadFile) => {
        imageUrl.value = URL.createObjectURL(uploadFile.raw!)
    };

    const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {//检查上传的文件是否为图片
        console.log(rawFile);
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

    async function request(params:any){//替换掉原本的xhr请求
        accountInfo.changeUserProfile(params);
    }
</script>

<style lang="scss" scoped>
    @use "sass:math";
    @import "commonScss";

    .avatar-uploader{
        @include fullInParentCircle();
        border: 1px dashed #00a8e9;
        overflow: hidden;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        position: relative;


        .avatar{
            @include fullInParent();
            position: relative;
            z-index:1;
        }

        .mock{
            @include fullInParent();
            position: absolute;
            top:0px;
            left:0px;
            z-index:2;
            background-color: black;
            opacity: 0;
            transition: opacity .3s ease;
            display: flex;
            justify-content: center;
            align-items: center;

            &:hover{
                opacity: 0.3;
            }

            .rowLine{
                background-color:white;
                @include fixedRetangle(3px, 17px);
                position: absolute;
                z-index: 1;
            }

            .columnLine{
                background-color:white;
                @include fixedRetangle(17px, 3px);
                position: absolute;
                z-index: 2;
            }
        }
    }
</style>
