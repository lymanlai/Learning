# 字节码 - 类文件结构

3409311825

### Class类文件的结构

Class文件是一组以8位字节为基础单位的二进制流。Class文件格式采用类似结构体的方式存储数据。

伪结构只有两种数据类型：无符号数和表。

<table class="table table-bordered table-striped table-condensed">
<th>
	<td>类型</td>
	<td>名称</td>
	<td>数量</td>
</th>
<tr>
	<td>u4</td>
	<td>magic</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>minor_version</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>major_version</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>constant_pool_count</td>
	<td>1</td>
</tr>
<tr>
	<td>cp_info</td>
	<td>constant_pool</td>
	<td>constant_pool_count-1</td>
</tr>
<tr>
	<td>u2</td>
	<td>access_flags</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>this_class</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>super_class</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>interfaces_count</td>
	<td>1</td>
</tr>
<tr>
	<td>u2</td>
	<td>interfaces</td>
	<td>interfaces_count</td>
</tr>
<tr>
	<td>u2</td>
	<td>fields_count</td>
	<td>1</td>
</tr>
<tr>
	<td>field_info</td>
	<td>fields</td>
	<td>fields_count</td>
</tr>
<tr>
	<td>u2</td>
	<td>methods_count</td>
	<td>1</td>
</tr>
<tr>
	<td>method_info</td>
	<td>methods</td>
	<td>methods_count</td>
</tr>
<tr>
	<td>u2</td>
	<td>attributes_count</td>
	<td>1</td>
</tr>
<tr>
	<td>attribute_info</td>
	<td>attributes</td>
	<td>attributes_count</td>
</tr>
</table>

### 常量池

常量池中有两大类常量：字面量（Literal）和符号引用（Symbolie References）。

其中符号引用包含：

* 类和结构的全限定名（Fully Qualified Name）
* 字段的名称和描述符（Descriptor）
* 方法的名称和描述符

### 实例的java代码

```java
public class hello {

	public static void main(String[] args){
			System.out.println("hello");
	}
}
```

### 实例的字节码

```java

// class文件的 magic number
cafe babe 
// class文件的 Minor Version
0000 
// class文件的 Magjor Version
0032 
// 创立
001c 0a00 0600 0f09
0010 0011 0800 120a 0013 0014 0700 1207
0015 0100 063c 696e 6974 3e01 0003 2829
5601 0004 436f 6465 0100 0f4c 696e 654e
756d 6265 7254 6162 6c65 0100 046d 6169
6e01 0016 285b 4c6a 6176 612f 6c61 6e67
2f53 7472 696e 673b 2956 0100 0a53 6f75
7263 6546 696c 6501 000a 6865 6c6c 6f2e
6a61 7661 0c00 0700 0807 0016 0c00 1700
1801 0005 6865 6c6c 6f07 0019 0c00 1a00
1b01 0010 6a61 7661 2f6c 616e 672f 4f62
6a65 6374 0100 106a 6176 612f 6c61 6e67
2f53 7973 7465 6d01 0003 6f75 7401 0015
4c6a 6176 612f 696f 2f50 7269 6e74 5374
7265 616d 3b01 0013 6a61 7661 2f69 6f2f
5072 696e 7453 7472 6561 6d01 0007 7072
696e 746c 6e01 0015 284c 6a61 7661 2f6c
616e 672f 5374 7269 6e67 3b29 5600 2100
0500 0600 0000 0000 0200 0100 0700 0800
0100 0900 0000 1d00 0100 0100 0000 052a
b700 01b1 0000 0001 000a 0000 0006 0001
0000 0002 0009 000b 000c 0001 0009 0000
0025 0002 0001 0000 0009 b200 0212 03b6
0004 b100 0000 0100 0a00 0000 0a00 0200
0000 0500 0800 0600 0100 0d00 0000 0200
0e

```

### javap命令输出常量表

```java

public class hello extends java.lang.Object
  SourceFile: "hello.java"
  minor version: 0
  major version: 50
  Constant pool:
const #1 = Method	#6.#15;	//  java/lang/Object."<init>":()V
const #2 = Field	#16.#17;	//  java/lang/System.out:Ljava/io/PrintStream;
const #3 = String	#18;	//  hello
const #4 = Method	#19.#20;	//  java/io/PrintStream.println:(Ljava/lang/String;)V
const #5 = class	#18;	//  hello
const #6 = class	#21;	//  java/lang/Object
const #7 = Asciz	<init>;
const #8 = Asciz	()V;
const #9 = Asciz	Code;
const #10 = Asciz	LineNumberTable;
const #11 = Asciz	main;
const #12 = Asciz	([Ljava/lang/String;)V;
const #13 = Asciz	SourceFile;
const #14 = Asciz	hello.java;
const #15 = NameAndType	#7:#8;//  "<init>":()V
const #16 = class	#22;	//  java/lang/System
const #17 = NameAndType	#23:#24;//  out:Ljava/io/PrintStream;
const #18 = Asciz	hello;
const #19 = class	#25;	//  java/io/PrintStream
const #20 = NameAndType	#26:#27;//  println:(Ljava/lang/String;)V
const #21 = Asciz	java/lang/Object;
const #22 = Asciz	java/lang/System;
const #23 = Asciz	out;
const #24 = Asciz	Ljava/io/PrintStream;;
const #25 = Asciz	java/io/PrintStream;
const #26 = Asciz	println;
const #27 = Asciz	(Ljava/lang/String;)V;

{
public hello();
  Code:
   Stack=1, Locals=1, Args_size=1
   0:	aload_0
   1:	invokespecial	#1; //Method java/lang/Object."<init>":()V
   4:	return
  LineNumberTable: 
   line 2: 0


public static void main(java.lang.String[]);
  Code:
   Stack=2, Locals=1, Args_size=1
   0:	getstatic	#2; //Field java/lang/System.out:Ljava/io/PrintStream;
   3:	ldc	#3; //String hello
   5:	invokevirtual	#4; //Method java/io/PrintStream.println:(Ljava/lang/String;)V
   8:	return
  LineNumberTable: 
   line 5: 0
   line 6: 8


}

```