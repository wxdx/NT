var seckillObj = {

    path : "",

    flag : "",

    url : {
        //获取商品唯一标识url
        randomURL : function () {
            return seckillObj.path + "/goods/random";
        },
        //秒杀地址
        seckillURL : function () {
            return seckillObj.path + "/goods";
        },
        //秒杀结果
        seckillResult : function () {
            return seckillObj.path + "/goods/result";
        }
    },

    func : {
        //1.秒杀信息初始化
        initDetail : function (startTime,endTime,nowTime,id) {
            if (nowTime < startTime){
                //未到秒杀时间,显示倒计时
                var killTime = new Date(startTime + 1000);
                $("#seckillTip").countdown(killTime,function (event) {
                    //时间格式
                    var format = event.strftime('距离秒杀开始还剩:%D天 %H时 %M分 %S秒');
                    $("#seckillTip").html("<span style='color: red'>"+format+"</span>");
                }).on('finish.countdown',function () {
                    //开始秒杀
                    seckillObj.func.startSeckill(id);
                })

            } else if (nowTime > endTime){
                //秒杀结束,显示秒杀结束
                $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>来晚啦,秒杀已结束!</span>");

            } else {
                //秒杀时间已到,显示秒杀按钮
                seckillObj.func.startSeckill(id);
            }
        },
        //2.秒杀开始函数
        startSeckill : function (id) {
            //秒杀时间到了,可以秒杀,通过ajax获取到商品唯一标识,如果接收到就可以秒杀,接收不到拒绝秒杀
            $.ajax({
                url : seckillObj.url.randomURL() + '/' + id,
                type : "POST",
                dataType:"json",
                success:function (messageObj) {
                    if (messageObj.errorCode == "0"){
                        var random = messageObj.data;
                        if (random) {
                            $("#seckillTip").html("<button type='button' id='seckillBtn'>立即秒杀</button>");
                            $("#seckillBtn").click(function () {
                                $("#seckillBtn").attr("disabled",true);
                                seckillObj.func.execSeckill(id,random);
                            })
                        }

                    } else {
                        $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>"+messageObj.errorMessage+"</span>");
                    }
                }

            })

        },
        execSeckill : function (id, random) {
            $.ajax({
                url : seckillObj.url.seckillURL() + '/' + id + '/' + random,
                type : "POST",
                dataType:"json",
                success:function (messageObj) {
                    if (messageObj.errorCode == "0"){
                        //秒杀请求成功
                        $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>"+messageObj.errorMessage+"</span>");

                        //3秒一次去查询下单结果
                        seckillObj.flag = window.setInterval(function () {
                            seckillObj.func.queryResult(id);
                        }, 3000);
                    } else {
                        $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>秒杀失败.....</span>");
                    }
                }
            })

        },
        queryResult : function (id) {
            $.ajax({
                url : seckillObj.url.seckillResult() + '/' + id,
                type : "POST",
                dataType : "json",
                success : function (messageObj) {
                    if (messageObj.errorCode == "0") {
                        //下单成功!
                        $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>秒杀成功,请<a href='https://www.alipay.com'>立即支付</a></span>");
                        clearInterval(seckillObj.flag);
                    } else if (messageObj.errorCode == "1"){
                        //下单失败!
                        $("#seckillTip").html("<span style='font-size: 15px;color: red;font-weight: bold'>"+messageObj.errorMessage+"</span>");
                        clearInterval(seckillObj.flag);
                    }
                }
            })

        }

    }
};