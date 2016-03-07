if (Meteor.isClient) {
  // counter starts at 0
  Meteor.startup(function(){
    React.render(<App />, document.getElementById("render-target"));
  });
}