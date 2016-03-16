# 在HTML中使用JavaScript

## <script> 元素

* async: 可选。表示应该立即下载脚本，但不应妨碍页面中的其他操作。只对外部脚本文件有效。

* charset: 可选。表示通过src属性指定的代码的字符集。已忽略。

* defer: 可选。表示通过src属性指定的代码的字符集。

* language: 已废弃。

* src: 表示包含要执行的外部文件。

* type:	表示编写代码使用的脚本语言的内容类型。

使用<script>元素的方式有两种：直接在页面中嵌入JavaScript代码和包含外部JavaScript文件。

### 标签的位置

```html
<!DOCTYPE html>
<html>
<head>
	<title>Example HTML Page</title>
	<script type="text/javascript" src="example1.js"></script>
	<script type="text/javascript" src="example2.js"></script>
</head>
<body>
<!-- body -->
</body>
</html>
```

文档的<head>元素中包含所有JavaScript文件，必须等到全部JavaScript代码都被下载，解析和执行完成以后，才能开始呈现页面的内容（浏览器在遇到<body>标签时才开始呈现内容）。如果需要加载很多JavaScript代码的页面来说，会出现明显的延迟，而延迟期间的浏览器窗口中将是一片空白。

```html
<!DOCTYPE html>
<html>
<head>
	<title>Example HTML Page</title>
</head>
<body>
<!-- body -->
<script type="text/javascript" src="example1.js"></script>
<script type="text/javascript" src="example2.js"></script>
</body>
</html>
```

Web应用程序一般都把全部JavaScript引用放在<body>元素中页面的内容后面。

### 延迟脚本

HTML4.01为<script>标签定义了defer属性。脚本会延迟加载，不影响页面构造。

```html
<!DOCTYPE html>
<html>
<head>
	<title>Example HTML Page</title>
	<script type="text/javascript" defer="defer" src="example1.js"></script>
	<script type="text/javascript" defer="defer" src="example2.js"></script>
</head>
<body>
<!-- body -->
</body>
</html>
```

在现实当中，延迟脚本并不一定会按照顺序执行，也不一定会在DOMContentLoaded事件触发前执行，因此最好只包含一个延迟脚本。

### 异步脚本

```html
<!DOCTYPE html>
<html>
<head>
	<title>Example HTML Page</title>
	<script type="text/javascript" async="async" src="example1.js"></script>
	<script type="text/javascript" async="async" src="example2.js"></script>
</head>
<body>
<!-- body -->
</body>
</html>
```

async只适用于外部脚本文件，并告诉浏览器立即下载文件。但与defer不同的是，标记为async的脚本并不保证按照指定它们的先后顺序执行。

### 在XHTML中的用法
