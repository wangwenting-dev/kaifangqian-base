package org.resrun.service.verify;


import org.resrun.controller.vo.response.VerifyResponse;
import org.resrun.service.pojo.SignPdfInfoVo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


/**
 * @Description: 在线签名验签服务实现类
 * @Package: org.resrun.service.verify
 * @ClassName: SignVerifyServiceImpl
 * @copyright 北京资源律动科技有限公司
 */
@Service
public class SignVerifyService{

    /**
     * 获取pdf签名图片信息
     * @return 提取结果
     */
    public VerifyResponse verify(byte[] bytes, String fileName) {
        SignPdfInfoVo signPdfInfo = new SignPdfInfoVo();

        try {
            signPdfInfo = VerifySign.getSignFromPdf(bytes);
            signPdfInfo.setPdfName(fileName);
            signPdfInfo.setPdfSize(String.format("%.2f",bytes.length/1024.0));
            switch (signPdfInfo.getPdfSingResult()) {
                case 1:
                    return new VerifyResponse(org.resrun.enums.SignStatus.SIGN_STATUS_NOSIGNATURE.getMsg(),signPdfInfo);
                case 2:
                    return new VerifyResponse(org.resrun.enums.SignStatus.SIGN_STATUS_ERROR.getMsg(), signPdfInfo);
                case 3:
                    return new VerifyResponse(org.resrun.enums.SignStatus.SIGN_STATUS_FIDDLE.getMsg(), signPdfInfo);
                default:
                    return new VerifyResponse(org.resrun.enums.SignStatus.SIGN_STATUS_RIGHT.getMsg(), signPdfInfo);
            }
        }catch (Exception e){
            signPdfInfo.setPdfName(fileName);
            signPdfInfo.setPdfSize(String.valueOf(bytes.length/1024));

            signPdfInfo.setPdfSingResult(org.resrun.enums.SignStatus.SIGN_STATUS_NOSIGNATURE.getCode());
            return new VerifyResponse(org.resrun.enums.SignStatus.SIGN_STATUS_NOSIGNATURE.getMsg(),signPdfInfo);
        }

    }
}
