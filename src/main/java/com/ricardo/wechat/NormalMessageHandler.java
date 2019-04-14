package com.ricardo.wechat;

import org.springframework.stereotype.Component;
import org.weixin4j.model.message.OutputMessage;
import org.weixin4j.model.message.normal.*;
import org.weixin4j.model.message.output.TextOutputMessage;
import org.weixin4j.spi.INormalMessageHandler;

/**
 * 公众号普通消息处理
 *
 * @author Ricardo
 * @date 2019/4/14
 */
@Component
public class NormalMessageHandler implements INormalMessageHandler {
    @Override
    public OutputMessage textTypeMsg(TextInputMessage textInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage imageTypeMsg(ImageInputMessage imageInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage voiceTypeMsg(VoiceInputMessage voiceInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage videoTypeMsg(VideoInputMessage videoInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage shortvideoTypeMsg(ShortVideoInputMessage shortVideoInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage locationTypeMsg(LocationInputMessage locationInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }

    @Override
    public OutputMessage linkTypeMsg(LinkInputMessage linkInputMessage) {
        return new TextOutputMessage("你的消息已收到，然而我并不想理你。");
    }
}
