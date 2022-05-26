package org.nethergames.proxytransport.encoder;

import com.nukkitx.protocol.util.Zlib;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.SneakyThrows;

import java.util.List;

@ChannelHandler.Sharable
public class ZlibEncoder extends MessageToMessageEncoder<ByteBuf> {

    @SneakyThrows
    public static ByteBuf compress(ByteBuf buf) {
        ByteBuf compressed = buf.alloc().directBuffer(1024 * 1024 * 2);
        Zlib.RAW.deflate(buf, compressed, 7);
        return compressed;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(compress(byteBuf));
    }
}