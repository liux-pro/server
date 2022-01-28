FROM registry.cn-beijing.aliyuncs.com/liux-pro/base-image:java as builder

WORKDIR /app

COPY . .

RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && bash build.bat

FROM registry.cn-beijing.aliyuncs.com/liux-pro/ubuntu:20.04

WORKDIR /app

COPY --from=builder /app/target/server  .


CMD ./server
