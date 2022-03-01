# 外网 gRPC

## proto介绍

*注意该目录是与移动端交互专用目录，用于外网gRPC proto协议，请勿存放非相关文件*

### proto package name:
> bilibili.{discovery_id}.{version}

例如：package bilibili.app.interface.v1;

bilibili 前缀主要用于SLB路由配置，进行转发到gRPC网关服务；  
所以，外网 gRPC proto 统一放在bilibili目录下面；  
其它仓库，可以直接以`git submodule`方式引入proto，并自动生成gRPC客户端代码。  

### proto Go Options

```
option go_package = "git.bilibili.co/bapis/bapis-go/bilibili/{discovery_id}/{version};{version}";
```

### proto Android Options

```
option java_multiple_files = true;
option java_package = "com.bapis.bilibili.{discovery_id}.{version}";
```

### proto IOS Options

```
option objc_class_prefix = "BAPI";
```

### stream

```
目前仅bilibili/broadcast目录支持rpc stream关键词。
```

### import依赖

```
该目录是与移动端交互专用目录，不可以import bilibili目前以外的proto文件（除github.com/gogo/protobuf/gogoproto/gogo.proto&&google/protobuf/*）
```

## metadata

客户端会传metadata在名为:x-bili-metadata-bin的header头内，内容为metadata.proto序列化后的字节。

### metadata字段定义

| name       | eg.                                           | remark    |
| ---------- | :-------------------------------------------- | :-------- |
| buvid      | 5848738A-7B27-474C-9C40-7F25701EFAC28527infoc | 设备buvid |
| build      | 5431000                                       | 构建号    |
| channel    | huawei                                        | 渠道      |
| mobi_app   | android/iphone/ipad/white...                  | 包类型    |
| platform   | android/ios                                   | 设备类型  |
| device     | phone                                         | 运行设备  |
| access_key | xxx                                           | 登录鉴权  |

## gRPC接入

服务端与客户端都基于proto文件自动生成网关代码，且基于http2进行gRPC请求。

### HTTP1.1降级请求

如果gRPC发生错误，那么客户端网关会自动进行降级，通过传统http1.1携带`Content-Type:application/grpc`头进行`POST`请求，其余协议完全按照标准的gRPC协议头和内容。

#### HTTP1.1降级后URL
POST: https://app.bilibili.com/{package}.{service}/{method}

如：https://app.bilibili.com/bilibili.app.interface.Search/Suggest3

#### HTTP1.1降级后Body (Request&Response)

> 1 byte of zero (not compressed).  
> 4 bytes of proto message length (big endian).  
> serialized proto message.  

#### References
* [移动端接口公共字段](https://info.bilibili.co/pages/viewpage.action?pageId=42503102)
* [gRPC HTTP/1.1 bridge](https://www.envoyproxy.io/docs/envoy/latest/configuration/http_filters/grpc_http1_bridge_filter)

## 调试工具
* [BloomRPC](https://github.com/uw-labs/bloomrpc)
