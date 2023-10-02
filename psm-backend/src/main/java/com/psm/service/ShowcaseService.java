package com.psm.service;
import com.psm.mapper.ShowCaseMapper;
import com.psm.utils.Result;
import com.psm.ato.ShowcaseBoxAto;
import com.psm.vo.ItemBoxVo;
import com.psm.vo.ShowcaseBoxVo;
import com.psm.vo.ShowcaseDetailBoxVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.psm.entity.ShowcaseBoxEntity;
import com.psm.entity.showcaseDetailEntity;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowcaseService {
    @Autowired
    private ShowCaseMapper showCaseMapper;

    public Result<?> getShowcaseBoxes(String token, ShowcaseBoxAto showcaseBoxAto) {
        try{
            List<ShowcaseBoxEntity> showcaseBoxEntityList;

            /**为减少查询数据库的IO操作，请求数据库时把account表、showcaseBox表、itemBox表的信息通过连接请求回来，实现请求一次数据库便获得全部橱窗盒子信息**/
            showcaseBoxEntityList = showCaseMapper.getShowcaseBoxes(showcaseBoxAto.getPrimarySort(), showcaseBoxAto.getLastSort(),
                    showcaseBoxAto.getIsIdle(), showcaseBoxAto.getCanQuicky(), showcaseBoxAto.getSortWay());

            /**由于一次性全部请求回来的信息有大量冗余，需要进行加工处理才能返回给前端**/
            List<String> authorIDList = new ArrayList<String>();//用于存储ShowcaseBoxEntity中authorID属性的数组，用于给showcaseBoxEntityList去重
            List<ShowcaseBoxVo> showcaseBoxVoList = new ArrayList<ShowcaseBoxVo>();//用于返回前端的结果数组

            showcaseBoxEntityList.forEach(item ->{
                /*给showcaseBoxEntityList去重*/
                if(!authorIDList.contains(item.getAuthorID())){//判断authorID是否在authorIDList内
                    authorIDList.add(item.getAuthorID());//若authorIDList没有该authorID，则加入数组

                    ShowcaseBoxVo showcaseBoxVo = new ShowcaseBoxVo();
                    BeanUtils.copyProperties(item,showcaseBoxVo);
                    showcaseBoxVo.setWorks("[]");
                    showcaseBoxVoList.add(showcaseBoxVo);
                };

                int index = authorIDList.indexOf(item.getAuthorID());

                ItemBoxVo itemBoxVo = new ItemBoxVo();
                BeanUtils.copyProperties(item,itemBoxVo);

                /*将works加工成json格式*/
                StringBuffer itemBoxVoToString = new StringBuffer();
                itemBoxVoToString.append("{\"ID\":\"");
                itemBoxVoToString.append(item.getWorkID());
                itemBoxVoToString.append("\",\"authorID\":\"");
                itemBoxVoToString.append(itemBoxVo.getAuthorID());
                itemBoxVoToString.append("\",\"price\":\"");
                itemBoxVoToString.append(itemBoxVo.getPrice());
                itemBoxVoToString.append("\",\"type\":\"");
                itemBoxVoToString.append(itemBoxVo.getType());
                itemBoxVoToString.append("\",\"imgPath\":\"");
                itemBoxVoToString.append(itemBoxVo.getImgPath());
                itemBoxVoToString.append("\",\"videoPath\":\"");
                itemBoxVoToString.append(itemBoxVo.getVideoPath());
                itemBoxVoToString.append("\",\"abstractInfo\":\"");
                itemBoxVoToString.append(itemBoxVo.getAbstractInfo());
                itemBoxVoToString.append("\",\"modifyTime\":\"");
                itemBoxVoToString.append(item.getWorkModifyTime());
                itemBoxVoToString.append("\"}");

                ShowcaseBoxVo base = showcaseBoxVoList.get(index);
                String baseWorks = base.getWorks();

                if(baseWorks.charAt(baseWorks.length()-2)=='['){
                    StringBuilder sb = new StringBuilder(baseWorks);
                    sb.insert(baseWorks.length()-1,itemBoxVoToString.toString());
                    base.setWorks(sb.toString());
                }
                else{
                    StringBuilder sb = new StringBuilder(baseWorks);
                    sb.insert(baseWorks.length()-1,","+itemBoxVoToString.toString());
                    base.setWorks(sb.toString());
                }
            });
            return Result.success(showcaseBoxVoList,"获取橱窗盒子成功");
        }catch (Exception e){
            return Result.error("500","获取橱窗盒子时发生错误:"+e.toString());
        }
    }

    public Result<?> getShowcaseBoxDetail(String ID){
        try {
            List<showcaseDetailEntity> list = showCaseMapper.getShowcaseBoxDetail(ID);
            ShowcaseDetailBoxVo showcaseDetailBoxVo = new ShowcaseDetailBoxVo();
            BeanUtils.copyProperties(list.get(0),showcaseDetailBoxVo);
            return Result.success( showcaseDetailBoxVo,"获取橱窗详情成功" );
        }
        catch (Exception e){
            return Result.error("500","获取橱窗详情时发生错误:"+e.toString());
        }
    }
}
