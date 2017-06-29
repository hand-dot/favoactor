var raspi = require('raspi-io');
var five = require('johnny-five');
var board = new five.Board({
  io: new raspi(),
  repl: false
});

board.on('ready', function() {
  var relay = new five.Relay({
    pin: "P1-13",
    type: "NC"
  });
  console.log('ON!');
  relay.on();
  setTimeout(function(){
  	relay.off();
    console.log('OFF!');
    process.exit();
  },100);
});