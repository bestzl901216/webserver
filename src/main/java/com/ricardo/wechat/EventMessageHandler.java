package com.ricardo.wechat;

import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.event.*;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.IEventMessageHandler;

/**
 * 公众号事件消息处理
 *
 * @author Ricardo
 * @date 2019/4/14
 */
@Component
public class EventMessageHandler implements IEventMessageHandler {
    @Override
    public OutputMessage subscribe(SubscribeEventMessage subscribeEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage unSubscribe(UnSubscribeEventMessage unSubscribeEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage qrsceneSubscribe(QrsceneSubscribeEventMessage qrsceneSubscribeEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage qrsceneScan(QrsceneScanEventMessage qrsceneScanEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage location(LocationEventMessage locationEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage click(ClickEventMessage clickEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage view(ViewEventMessage viewEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage scanCodePush(ScanCodePushEventMessage scanCodePushEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage scanCodeWaitMsg(ScanCodeWaitMsgEventMessage scanCodeWaitMsgEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage picSysPhoto(PicSysPhotoEventMessage picSysPhotoEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage picPhotoOrAlbum(PicPhotoOrAlbumEventMessage picPhotoOrAlbumEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage picWeixin(PicWeixinEventMessage picWeixinEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }

    @Override
    public OutputMessage locationSelect(LocationSelectEventMessage locationSelectEventMessage) {
        return new TextOutputMessage("别再来烦我，别来管我。我只是不一样的烟火！");
    }
}
