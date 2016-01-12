# Scss Guide

## 文件后缀名

```css
//文件后缀名为sass的语法
body 
	background: #eee
	font-size: 12px
p
	background: #0982c1

//文件后缀名为scss的语法
body{
	background: #eee;
	font-size: 12px;
}
p{
	background: #0982c1;
}
```

## 导入

```css
// a.scss
//---------------------------------
body{
	background: #eee;
}
```

```css
// b.scss
//---------------------------------
@import "reset.css";
@import "a";
p{
	background: #0982c1;
}
```

```css
// 编译后的 b.css
//---------------------------------
@import "reset.css";
body{
	background: #eee;
}
p{
	background: #0982c1;
}
```

## 变量

### 普通变量

```css
// sass style
//---------------------------------
$fontSize: 12px;
body{
	font-size:$fontSize;
}

// css style
//---------------------------------
body{
	font-size: 12px;
}
```

### 默认变量

```css
//sass style
//---------------------------------
$baseLineHeight: 1.5 !default;
body{
	line-heigth: $baseLineHeight;
}

//css style
//---------------------------------
body{
	line-height: 1.5;
}
```

覆盖
```css
//sass style
//--------------------------------- 
$baseLineHeight: 2;
$baseLineHeight: 1.5 !default;
body{
	line-height: $baseLineHeight;
}

//css style
//--------------------------------- 
body{
	line-height: 2;
}
```

### 特殊变量

```css
//sass style
//--------------------------------- 
$borderDirection: top !default;
$baseFontSize: 12px !default;
$baseLineHeight: 1.5 !default;

.border-#{$borderDirection}{
	border-#{$borderDirection}:1px solid #ccc;
}

body{
	font:#{$baseFontSize}/#{$baseLineHeight};
}

//css style
//---------------------------------
.border-top{
	border-top: 1px solid #ccc;
}
body{
	font: 12px/1.5;
}
```

### 多值变量

#### list

##### 定义

```css
// 一维数组
$px: 5px 10px 20px 30px;

// 二维数组
$px: 5px 10px, 20px, 30px;
$px: (5px, 10px) (20px 30px);
```

##### 使用

```css
//sass style
//---------------------------------
$linkColor: #08c #333 !default;
a{
	color:nth($linkColor, 1);
	&:hover{
		color:nth($linkColor, 2);
	}
}

//css style
//---------------------------------
a{
	color: #08c;
}
a:hover{
	color: #333;
}
```

#### map

##### 定义

```css
$heading: (h1: 2em, h2: 1.5em, h3: 1.2em);
```

##### 使用

```css
//sass style
//---------------------------------
$headings: (h1: 2em, h2: 1.5em, h3: 1.2em);
@each $header, $size in $headings{
	#{$header}{
		font-size: $size;
	}
}

//css style
//---------------------------------
h1{
	font-size: 2em;
}
h2{
	font-size: 1.5em;
}
h3{
	font-size: 1.2em;
}
```

### 全局变量

#### 3.4 版本以前

```css
//sass style
//---------------------------------
$fontSize: 12px;
body{
	$fontSie: 14px;
	font-size: $fontSize;
}
p{
	font-size: $fontSize;
}

//css style
//---------------------------------
body{
	font-size: 14px;
}
p{
	font-size: 14px;
}
```

#### 3.4 版本以后

```css
//sass style
//---------------------------------
$fontSize: 12px;
$color: #333;
body{
	$fontSize: 14px;
	color: #fff; !global;
	font-size: $fontSize;
	color: $color;
}
p{
	font-size: $fontSize;
	color: $color;
}

//css style
//---------------------------------
body{
	font-size: 14px;
	color: #fff;
}
p{
	font-size: 12px;
	color: #fff;
}
```

## 嵌套

### 选择器嵌套

```css
//sass style
//---------------------------------
#top_nav{
	line-height: 40px;
	text-transform: capitalize;
	background-color: #333;
	li{
		float: left;
	}
	a{
		display: block;
		padding: 0 10px;
		color: #fff;

		&:hover{
			color: #ddd;
		}
	}
}

//css style
//---------------------------------
#top_nav{
	line-height: 40px;
	text-transform: capitalize;
	background-color: #333;
}
#top_nav li{
	float: left;
}
#top_nav a{
	display: block;
	padding: 0 10px;
	color: #fff;
}
#top_nav a:hover{
	color: #ddd;
}
```

#### 属性嵌套

```css
//sass style
//---------------------------------
.fakeshadow{
	border: {
		style: solid;
		left: {
			width: 4px;
			color: #888;
		}
		right: {
			width: 2px;
			color: #ccc;
		}
	}
}

//css style
//---------------------------------
.fakeshadow{
	border-style: solid;
	border-left-width: 4px;
	border-left-color: #888;
	border-right-width: 2px;
	border-right-color: #ccc;
}
```

## @at-root

```css
//没有跳出
.parent-1{
	color: #f00;
	.child{
		width: 100px;
	}
}

//单个选择器跳出
.parent-2{
	color: #f00;
	@at-root .child{
		@at-root .child{
			width: 200px;
		}
	}
}

//多个选择器跳出
.parent-3{
	background: #f00;
	@at-root{
		.child1{
			width: 300px;
		}
		.child2{
			width: 400px;
		}
	}

}

//css
//---------------------------------
.parent-1 {
  color: #f00;
}
.parent-1 .child {
  width: 100px;
}

.parent-2 {
  color: #f00;
}
.child {
  width: 200px;
}

.parent-3 {
  background: #f00;
}
.child1 {
  width: 300px;
}
.child2 {
  width: 400px;
}
```

#### @at-root (without:...)和@at-root(with:...)

```css
//sass style
//---------------------------------
//  跳出父级元素嵌套
@media print{
	.parent1{
		color: #f00;
		@at-root .child1{
			width: 200px;
		}
	}
}

//跳出media嵌套，父级有效
@media print{
	.parent2{
		color: #foo;
		@at-root(without: media){
			.child2{
				width: 200px;
			}
		}
	}
}

//跳出media和父级
@media print{
	.parent3{
		color: #f00;
		@at-root(without: all){
			.child3{
				width: 200px;
			}
		}
	}
}

//sass style
//-------------------------------
@media print {
  .parent1 {
    color: #f00;
  }
  .child1 {
    width: 200px;
  }
}

@media print {
  .parent2 {
    color: #f00;
  }
}
.parent2 .child2 {
  width: 200px;
}

@media print {
  .parent3 {
    color: #f00;
  }
}
.child3 {
  width: 200px;
}

```

#### @at-root 与 & 配合使用

```css
//sass style
//---------------------------------
.child{
	@at-root .parent &{
		color: #f00;
	}
}

//css style
//---------------------------------
.parents .child{
	color: #f00;
}
```

#### 应用于@keyframe

```css
//sass style
//---------------------------------
.demo{
	...
	animation: motion 3s infinite;
	@at-root {
		@keyframes motion{
			...
		}
	}
}

//css
//---------------------------------
.demo {
	...
	animation: motion 3s infinite;
}
@keyframs motion{
	...
}
```

## 混合(mixin)

#### 无参数mixin

```css
//sass style
//---------------------------------
@mixin center-block{
	margin-left: auto;
	margin-right: auto;
}
.demo{
	@include center-block;
}

//css style
//---------------------------------
.demo{
	margin-left: auto;
	margin-right: auto;
}
```

#### 有参数mixin

```css
//sass style
//---------------------------------
@mixin opacity($opacity: 50){
	opacity: $opacity / 100;
	filter: alpha(opacity=$opacity);
}

//css
//---------------------------------
.opacity{
	@include opacity;
}
.opacity-80{
	@include opacity(80);
}
```

#### 多个参数mixin

```css
//sass style
//---------------------------------
@mixin horizontal-line($border: 1px dashed #ccc, $padding: 10px){
	border-bottom: $border;
	padding-top: $padding;
	padding-bottom: $padding;
}
.imgtext-h li{
	@include horizontal-line(1px solid #ccc);
}
.imgtext-h--product li{
	@include horizontal-line($padding: 15px);
}

css style
//---------------------------------
.imgtext-h li{
	border-bottom: 1px solid #cccccc;
	padding-top: 10px;
	padding-bottom: 10px;
}
.imgtext-h--product li{
	border-bottom: 1px dashed #cccccc;
	padding-top: 15px;
	padding-botom: 15px;
}
```

#### 多组值参数mixin

```css
@mixin box-shadow($shadow...) {
  -webkit-box-shadow:$shadow;
  box-shadow:$shadow;
}
.box{
  border:1px solid #ccc;
  @include box-shadow(0 2px 2px rgba(0,0,0,.3),0 3px 3px rgba(0,0,0,.3),0 4px 4px rgba(0,0,0,.3));
}

//css style
//-------------------------------
.box{
  border:1px solid #ccc;
  -webkit-box-shadow:0 2px 2px rgba(0,0,0,.3),0 3px 3px rgba(0,0,0,.3),0 4px 4px rgba(0,0,0,.3);
  box-shadow:0 2px 2px rgba(0,0,0,.3),0 3px 3px rgba(0,0,0,.3),0 4px 4px rgba(0,0,0,.3);
}
```

#### @content

```css
//sass style
//---------------------------------
@mixin max-screen($res){
	@media only screen and (max-width: $res){
		@content;
	}
}

@inclue max-screen(480px){
	body {color: red}
}

//css style
//---------------------------------
@media only screen and (max-width: 480px){
	body {color: red}
}
```

##继承

```css
//sass style
//---------------------------------
h1{
	boder: 4px solid #ff9aa9;
}
.speaker{
	@extend h1;
	border-width: 2px;
}

//css style
//---------------------------------
h1, speaker{
	border: 4px solid #ff9aa9;
}
.speaker{
	border-width: 2px;
}
```

### 占位选择器%

```css
//sass style
//---------------------------------
%ir{
	color: transparent;
	text-shadow: none;
	background-color: transparent;
	border: 0;
}
%clearfix{
	@if $lte7{
		*zoom: 1;
	}
	&:before,
	&:after{
		content: "";
		display: table;
		font: 0/0 a;
	}
	&:after{
		clear: both;
	}
}
#header{
	h1{
		@extend %ir;
		width: 200px;
	}
}
.ir{
	@extends %ir;
}

//css
//---------------------------------
#header h1,
.ir{
	color: transparent;
	text-shadow: none;
	background-color: transparent;
	border: 0;
}
#header h1{
	width: 300px;
}
```

##函数

```css
//sass style
//--------------------------------
$baseFontSize: 10px !default;
$grap:         #ccc !default;

@function pxToRem($px){
	 @return $px / $baseFontSize * 1rem;
}

body{
	font-size: $baseFontSize;
	color:lighten($grap, 10%);
}
.test{
	font-size:pxToRem(16px);
	color: darken($gray, 10%);
}

//css style
//---------------------------------
body{
	font-size: 10px;
	color: #e6e6e6;
}
.test{
	font-size: 1.6rem;
	color: #b3b3b3;
}
```

## 运算

```css
$baseFontSize:		14px !default;
$baseLineHeight: 	1.5 !default;
$baseGap:			$baseFontSize * $baseLineHeight !default;
$halfBaseGap:		$baseGap / 2 !default;
$samllFontSize: 	$baseFontSize - 2px !default;

$_columns: 			12 !default;
$_column-width: 	60px !default;
$_gutter:			20px !default;
$_gridsystem-width:	$_colums * ($_column-width + $_gutter);
```

## 条件判断及循环
### @if 判断

```css
//sass style
//---------------------------------
$lte7: true
$type: monster;
.ib{
	display:inline-block;
	@if $lte7{
		*display:inline;
		*zoom:1;
	}
}
p{
	@if $type == ocean{
		color: blue;
	} @else if $type == matador{
		color: red;
	} @else if $type == matador{
		color: green;
	} @else {
		color: black;
	}
}

//css
//---------------------------------
.ib{
	display:inline-block;
	*display:inline;
	*zoom:1;
}
p{
	color: green;
}
```

### 三目判断

```css
if(true, 1px, 2px) => 1px
if(false, 1px, 2px) => 2px
```

### for循环

```css
@for $i from 1 through 3 {
	.item-#{$i} { width: 2em * $i; }
}

//css style
//---------------------------------
.item-1{
	width: 2em;
}
.item-2{
	width: 4em;
}
.item-3{
	width: 6em;
}
```

### @each 循环

#### 单个字段list数据循环

```css
//sass style
//---------------------------------
$animal-list: puma, sea-slug, egret, salamander;
@each $animal in $anmimal-list {
	.#{$animal}-icon{
		backgroud-image: url('/images/#{$animal}.png');
	}
}

//css style
//-------------------------------
.puma-icon {
  background-image: url('/images/puma.png'); 
}
.sea-slug-icon {
  background-image: url('/images/sea-slug.png'); 
}
.egret-icon {
  background-image: url('/images/egret.png'); 
}
.salamander-icon {
  background-image: url('/images/salamander.png'); 
}
```

#### 多个字段list数据循环

```css
//sass style
//-------------------------------
$animal-data: (puma, black, default),(sea-slug, blue, pointer),(egret, white, move);
@each $animal, $color, $cursor in $animal-data {
  .#{$animal}-icon {
    background-image: url('/images/#{$animal}.png');
    border: 2px solid $color;
    cursor: $cursor;
  }
}

//css style
//-------------------------------
.puma-icon {
  background-image: url('/images/puma.png');
  border: 2px solid black;
  cursor: default; 
}
.sea-slug-icon {
  background-image: url('/images/sea-slug.png');
  border: 2px solid blue;
  cursor: pointer; 
}
.egret-icon {
  background-image: url('/images/egret.png');
  border: 2px solid white;
  cursor: move; 
}
```

#### 多个字段map数据循环

```css
//sass style
//-------------------------------
$headings: (h1: 2em, h2: 1.5em, h3: 1.2em);
@each $header, $size in $headings {
  #{$header} {
    font-size: $size;
  }
}

//css style
//-------------------------------
h1 {
  font-size: 2em; 
}
h2 {
  font-size: 1.5em; 
}
h3 {
  font-size: 1.2em; 
}
```