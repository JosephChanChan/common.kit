# common.kit 这是一个通用的工具类包

sign包提供验证签名的脚手架，只需要实现自定义的密钥加载逻辑和原始串拼接逻辑，配合上@SignCheck注解框架就会自动帮忙进行签名验证。
签名的结果可在业务代码中获取，让用户自行决定业务结果。

collections包提供集合常用的场景的解决方案，判空，对数据分页等。
