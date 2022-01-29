FROM registry.cn-beijing.aliyuncs.com/liux-pro/base-image:server as builder
WORKDIR /app
COPY . .
RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo -DskipTests package

FROM registry.cn-beijing.aliyuncs.com/liux-pro/ubuntu:20.04
WORKDIR /app
COPY --from=builder /app/target/server  .
CMD ./server
