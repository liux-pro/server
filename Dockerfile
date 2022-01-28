FROM registry.cn-beijing.aliyuncs.com/liux-pro/base-image:java@sha256:48bbb33c1c294d2b26814da3bddd0b75a3bdf0e5165a73eec5e3aad4b82195db as builder



COPY pom.xml .

RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo dependency:resolve

WORKDIR /app

COPY . .

RUN . "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -Pnative -Phuawei-repo -DskipTests package

FROM registry.cn-beijing.aliyuncs.com/liux-pro/ubuntu:20.04

WORKDIR /app

COPY --from=builder /app/target/server  .


CMD ./server
