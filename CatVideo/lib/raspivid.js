var Class     = require('uclass');
var spawn        = require('child_process').spawn;
var util      = require('util');
var Server    = require('./_server');


var RpiServer = new Class({
  Extends : Server,

  options: {
    height: 512,
    width: 768,	
    rot: 90,
    fps : 30,
  },

  get_feed : function(){

    var msk = "raspivid -rot %d -vf -t 0 -o - -w %d -h %d -fps %d";

    var cmd = util.format(msk,this.options.rot, this.options.width, this.options.height, this.options.fps);
    console.log(cmd);
    streamer = spawn('raspivid', ['-rot', this.options.rot, '-vf', '-t', '0', '-o', '-', '-w', this.options.width, '-h', this.options.height, '-fps', this.options.fps, '-pf', 'baseline']);

    streamer.on("exit", function(code){
      console.log("Failure", code);
      return streamer;
    });
        
    return streamer;
  },


});


module.exports = RpiServer;
