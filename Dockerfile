FROM registry.cn-beijing.aliyuncs.com/liux-pro/base-image:java as builder

WORKDIR /app

COPY pom.xml .

RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Phuawei-repo dependency:go-offline

COPY . .

RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo -DskipTests clean package

FROM registry.cn-beijing.aliyuncs.com/liux-pro/ubuntu:20.04

WORKDIR /app

COPY --from=builder /app/target/server  .


CMD ./server
