package com.translate.web.controller;

import com.translate.baidu.api.SpeechApi;
import com.translate.baidu.api.TranslateApi;
import com.translate.common.util.OssUtil;
import com.translate.google.api.GoogleApi;
import com.translate.web.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/19 9:44
 * @Description:
 */
@Api(tags = "API接口")
@RestController
@RequestMapping("/api/v1")
public class TranslateController {


    @ApiOperation(value = "百度翻译", httpMethod = "POST", notes = "首页翻译")
    @PostMapping("/translate")
    @ResponseBody
    public Map<String,Object> translate(
            @ApiParam(name = "from", value = "原文", required = true) @RequestParam("from") String from,
            @ApiParam(name = "auto", value = "翻译语言", required = true) @RequestParam("auto") String auto,
            HttpServletRequest request) {
        Map<String,Object> apiJsonResult = new HashMap<String, Object>();
        TranslateApi api = new TranslateApi();
        try {
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("from",from);
            result.put("auto",auto);

            JsonResult jsonResult = api.getTransResult(from, auto);

            result.put("to",jsonResult.getMsg());

            apiJsonResult.put("success",jsonResult.getSuccess());
            apiJsonResult.put("code",jsonResult.getCode());
            apiJsonResult.put("msg",result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return apiJsonResult;
    }


    @ApiOperation(value = "谷歌翻译", httpMethod = "POST", notes = "首页翻译")
    @PostMapping("/googleTranslate")
    @ResponseBody
    public Map<String,Object> googleTranslate(
            @ApiParam(name = "from", value = "原文", required = true) @RequestParam("from") String from,
            @ApiParam(name = "auto", value = "翻译语言", required = true) @RequestParam("auto") String auto,
            HttpServletRequest request) {
        Map<String,Object> apiJsonResult = new HashMap<String, Object>();
        GoogleApi api = new GoogleApi();
        try {
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("from",from);
            result.put("auto",auto);

            JsonResult jsonResult = api.translate(from,auto);

            result.put("to",jsonResult.getMsg());

            apiJsonResult.put("success",jsonResult.getSuccess());
            apiJsonResult.put("code",jsonResult.getCode());
            apiJsonResult.put("msg",result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return apiJsonResult;
    }

    @ApiOperation(value = "语音合成", httpMethod = "POST", notes = "首页翻译")
    @PostMapping("/speech")
    @ResponseBody
    public Map<String,Object> speech(
            @ApiParam(name = "txt", value = "文本内容", required = true) @RequestParam("txt") String txt,
            HttpServletRequest request) {
        Map<String,Object> apiJsonResult = new HashMap<String, Object>();
        try {
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("from",txt);

            String url = SpeechApi.synthesis(txt);

            String url1 = OssUtil.getUrl(url, true);

            result.put("to",url1);

            apiJsonResult.put("success","true");
            apiJsonResult.put("code","0");
            apiJsonResult.put("msg",result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return apiJsonResult;
    }
}
