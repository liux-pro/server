FROM registry.cn-hongkong.aliyuncs.com/liux-pro/base-image:server as prepare
ADD pom.xml .
RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -ntp -Pnative -Pspring-repo dependency:resolve

FROM prepare as builder
WORKDIR /app
COPY . .
RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -ntp -Pnative -Pspring-repo -DskipTests package

FROM ubuntu:20.04
WORKDIR /app
COPY --from=builder /app/target/server  .
CMD ./server
