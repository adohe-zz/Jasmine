package com.xqbase.gatekeeper.tcp.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * TcpMessageEncoder encodes the tcp message to byte stream.
 *
 * @author Tony He
 */
@ChannelHandler.Sharable
public class TcpMessageEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }
}
