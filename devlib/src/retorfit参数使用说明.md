@Body	直接指定 PUT/POST 的请求体，但并非作为请求参数或者表单的请求体
@Field	表单字段
@FieldMap	以键值对方式设置表单字段
@Header	添加请求头（不固定的请求头）
@HeaderMap	以 Map 形式添加请求头
@Headers	添加包含值的请求头（固定的请求头）
@Part	表示一个多部分请求的单个部分（多用于文件上传）
@PartMap	表示一个多部分请求的 name 和 value 字段（多用于文件上传）
@Path	替换 URL 中被 {} 包裹起来的字段
@Query	向 url 追加参数
@QueryMap	向 url 追加键值对参数
@QueryName	为没有 value 的 name 字段传值
@Url	使用全路径复写 baseUrl，用于非统一 baseUrl 的场景

