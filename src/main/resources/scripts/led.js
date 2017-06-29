var raspi = require('raspi-io');
var five = require('johnny-five');
var board = new five.Board({
  io: new raspi(),
  repl: false
});

board.on('ready', function() {
  var led = new five.Led('P1-11');
  console.log('ON!');
  led.on();
  setTimeout(function(){
  	led.off();
    console.log('OFF!');
    process.exit();
  },1000);
});