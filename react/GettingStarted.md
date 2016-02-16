# Getting Started

## JSFiddle

The easiest way to start hacking on React is using the following JSFiddle Hello World examples:

* React JSFiddle

```html
<div id="container">
	<!-- This elemet's contents will be replaced with your componet. -->
</div>
```

```javascript
var Hello = React.createClass({
	displayName: 'Hello',
	render: function(){
		return React.createElement("div", null, "Hello ", this.props.name);
	}
});

ReactDOM.render(
	React.createElement(Hello, {name: "World"}),
	document.getElementById('container')
);
```

* React JSFiddle without JSX

```html
<script src="https://facebook.github.io/react/js/jsfiddle-integration-babel.js"></script>

<div id="container">
    <!-- This element's contents will be replaced with your component. -->
</div>
```

```javascript
var Hello = React.createClass({
	render: function(){
		return <div>Hello {this.props.name}</div>;
	}
});

ReactDOM.render(
	<Hello name="World" />,
	document.getElementById('container')
);
```
## Using React from npm


