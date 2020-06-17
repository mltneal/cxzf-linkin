//package com.unionpay.cxzflinkin.controller;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.unionpay.cxzflinkin.utils.RSA2Utils;
//import com.unionpay.cxzflinkin.utils.RSAUtils;
//import org.apache.commons.lang3.RandomUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.bwton.core.open.RequestHeader;
//import com.bwton.core.open.ResponseHeader;
//import com.bwton.core.servlet.RequestWrapper;
//import com.bwton.core.servlet.ResponseWrapper;
//import com.bwton.core.trace.TraceThreadLocal;
//import com.bwton.core.util.HttpUtils;
//import com.bwton.core.web.CommonErrors;
//import com.bwton.core.web.CommonResult;
//import com.bwton.lang.Result;
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//public class AppLinkInController  {
//
////    @Autowired
////    private Gson gson;
////
////    //需要上传应用公钥和平台私钥 及对应的appId
////    @RequestMapping("test")
////    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
////                                    FilterChain filterChain) throws ServletException, IOException {
////
////        com.bwton.core.servlet.RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);
////        com.bwton.core.servlet.ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
////        String appid = httpServletRequest.getHeader(RequestHeader.AppID);
////        String sequence = httpServletRequest.getHeader(RequestHeader.Sequence);
////        //用appId+sequence作为跟踪号
////        boolean isBegin = TraceThreadLocal.setTraceIdIfNotTracing(appid + "-" + sequence);
////
////        try {
////            Result error = null;
////
////            String requestUri = httpServletRequest.getRequestURI();
////            int idx = requestUri.indexOf("/");
////
////            if (idx <= 0) {
////                error = CommonResult
////                        .failure(CommonErrors.SERVICE_NOT_EXIST.getCode(), httpServletRequest.getRequestURI() + "不存在");
////            }
////
////            if (error == null) {
////                error = verifyRequest(trafficBizInfo, requestWrapper);
////            }
////
////            if (error == null) {
////                try {
////                    filterChain.doFilter(requestWrapper, responseWrapper);
////                } catch (Throwable t) {
////                    log.error("invoke API error", t);
////                    error = CommonResult.failure(t);
////                }
////            }
////
////            signResponse(trafficBizInfo, requestWrapper, responseWrapper, error);
////        } finally {
////            if (isBegin) {
////                TraceThreadLocal.clear();
////            }
////        }
////
////    }
////
////    private Result verifyRequest( RequestWrapper request) throws IOException {
////        RequestHeader header = new RequestHeader(request);
////        String remote = request.getRemoteHost();
////
////        //log.debug("host {} call api: {}", remote, client);
////        log.info("uri: {}, header: {}", request.getRequestURI(), header);
////        if (StringUtils.isEmpty(header.getAppId())) {
////            return CommonResult.failure(CommonErrors.INVALID_APPID);
////        } else {
////            String encoding = HttpUtils.getEncoding(request, "utf-8");
////            String message = new String(request.getDataStream(), encoding);
////            log.info("请求报文:{}", message);
////            log.debug("请求签名 {}", header.getSignature());
////            boolean verify = false;
////
////            String decryptedRandom;
////            try {
////                decryptedRandom = message;
////                if (StringUtils.isNotEmpty(header.getTimestamp()) && StringUtils.isNotEmpty(header.getNonce())) {
////                    decryptedRandom = RequestHeader.getSignString(header, message);
////                }
////
////                log.debug("toBeVerified={}", decryptedRandom);
////                if (StringUtils.equalsAnyIgnoreCase(header.getSignType(), "RSA2")) {
////                    log.debug("使用RSA2验签");
////                    verify = RSA2Utils
////                            .doCheck(decryptedRandom, header.getSignature(), trafficBizInfo.getPublic_secret(), encoding);
////                } else {
////                    log.debug("使用RSA验签");
////                    verify = RSAUtils
////                            .doCheck(decryptedRandom, header.getSignature(), trafficBizInfo.getPublic_secret(), encoding);
////                }
////            } catch (Exception e) {
////                log.warn("check signature error. signature={}, signType={}, appid={}", header.getSignature(),
////                        header.getSignType(), header.getAppId(), e);
////            }
////
////            if (!verify) {
////                return CommonResult.failure(CommonErrors.SIGNATURE_VERIFY_FAIL);
////            } else {
////
////                try {
////                    if (StringUtils.isNotEmpty(header.getRandom())) {
////                        log.debug("encrypted request random=>{}", header.getRandom());
////                        if (StringUtils.equalsAnyIgnoreCase(header.getSignType(), "RSA2")) {
////                            decryptedRandom = RSA2Utils
////                                    .decryptByPrivateKey(header.getRandom(), trafficBizInfo.getPlatform_private_secret());
////                        } else {
////                            decryptedRandom = RSAUtils
////                                    .decryptByPrivateKey(header.getRandom(), trafficBizInfo.getPlatform_private_secret());
////                        }
////
////                        log.debug("decrypted request random=>{}", decryptedRandom);
////                        request.setHeader("Random", decryptedRandom);
////                    }
////                } catch (Exception e) {
////                    return CommonResult.failure("random解密失败");
////                }
////
////                return null;
////            }
////        }
////    }
////
////    private void signResponse(TrafficBizInfo trafficBizInfo, RequestWrapper request, ResponseWrapper response,
////                              Result error) throws IOException {
////        RequestHeader requestHeader = new RequestHeader(request);
////        String encoding = HttpUtils.getEncoding(request, "utf-8");
////
////        response.setHeader("Sequence", requestHeader.getSequence());
////        response.setHeader("Content-Type", request.getHeader("Content-Type"));
////        String message;
////        if (error == null) {
////            if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
////                response.setStatus(200);
////                response.resetBuffer();
////                message = this.gson.toJson(CommonResult.failure(CommonErrors.SERVICE_NOT_EXIST));
////            } else if (response.getStatus() == HttpStatus.UNAUTHORIZED.value()) {
////                response.setStatus(200);
////                response.resetBuffer();
////                message = this.gson.toJson(CommonResult.failure(CommonErrors.TOKEN_VERIFY_FAIL));
////            } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
////                response.setStatus(200);
////                response.resetBuffer();
////                message = this.gson.toJson(CommonResult.failure(CommonErrors.UNDEFINED));
////            } else {
////                message = new String(response.getDataStream(), encoding);
////            }
////        } else {
////            message = this.gson.toJson(error);
////        }
////
////        log.info("Outgoing message=>{}", message);
////
////        try {
////            String toBeSigned = message;
////            String timestamp = requestHeader.getTimestamp();
////            String nonce = requestHeader.getNonce();
////            if (StringUtils.isNotEmpty(timestamp) && StringUtils.isNotEmpty(nonce)) {
////                timestamp = System.currentTimeMillis() + "";
////                nonce = RandomUtils.nextLong() + "";
////                response.setHeader("AppID", requestHeader.getAppId());
////                response.setHeader("Timestamp", timestamp);
////                response.setHeader("Nonce", nonce);
////                toBeSigned = ResponseHeader
////                        .getSignString(requestHeader.getVersion(), requestHeader.getAppId(), message, nonce,
////                                requestHeader.getSequence(), timestamp);
////            }
////
////            log.debug("toBeSigned={}, signType={}", toBeSigned, requestHeader.getSignType());
////            String signature;
////
////            if (trafficBizInfo != null && StringUtils.isNotBlank(trafficBizInfo.getPlatform_private_secret())) {
////                if (StringUtils.equalsIgnoreCase(requestHeader.getSignType(), "RSA2")) {
////                    signature = RSA2Utils.sign(toBeSigned, trafficBizInfo.getPlatform_private_secret(), encoding);
////                } else {
////                    signature = RSAUtils.sign(toBeSigned, trafficBizInfo.getPlatform_private_secret(), encoding);
////                }
////
////                response.setHeader("Signature", signature);
////                log.debug("response signature=>{}", signature);
////            } else {
////                log.debug("无法获取密钥，不对应答报文进行签名");
////            }
////
////
////        } catch (Exception var12) {
////            throw new RuntimeException("sign response error", var12);
////        }
////
////        response.getWriter().write(message);
////    }
//}
