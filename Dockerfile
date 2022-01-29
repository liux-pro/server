FROM registry.cn-beijing.aliyuncs.com/liux-pro/base-image:java as prepare
ADD pom.xml .
RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo dependency:resolve

FROM prepare as builder
WORKDIR /app
COPY . .
RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo -DskipTests package

FROM registry.cn-beijing.aliyuncs.com/liux-pro/ubuntu:20.04
WORKDIR /app
COPY --from=builder /app/target/server  .
CMD ./server
