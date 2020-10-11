<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Video HTML5</title>

 <style>
        .mainContainer {
    display: block;
    width: 1024px;
    margin-left: auto;
    margin-right: auto;
}
 
.urlInput {
    display: block;
    width: 100%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 8px;
    margin-bottom: 8px;
}
 
.centeredVideo {
    display: block;
    width: 536px;
    height: 300px;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: auto;
}
 
.controls {
    display: block;
    width: 100%;
    text-align: left;
    margin-left: auto;
    margin-right: auto;
}
    </style>

</head>
<body>



 <table>
        <tr>
            <td>
                <video id="videoElement1" class="centeredVideo" controls autoplay width="750" height="350">
                    Your browser does not support the video tag.
                </video>
            </td>
            <td>
                <video id="videoElement2" class="centeredVideo" controls autoplay width="750" height="350">
                    Your browser does not support the video tag.
                </video>
            </td>
        </tr>
        <tr>
            <td>
                <video id="videoElement3" class="centeredVideo" controls autoplay width="750" height="350">
                    Your browser does not support the video tag.
                </video>
            </td>
            <td>
                <video id="videoElement4" class="centeredVideo" controls autoplay width="750" height="350">
                    Your browser does not support the video tag.
                </video>
            </td>
        </tr>
    </table>


    <script type="text/javascript" src="<%=request.getContextPath()%>/js/flv.min.js"></script> 

 <script>
        if (flvjs.isSupported()) {

            var videoElement1 = document.getElementById('videoElement1');
            // videoElement1.muted = false; 
            var flvPlayer1 = flvjs.createPlayer({
                isLive: true,
                type: 'flv',
                url: '${videos.video1}'
            });
            flvPlayer1.attachMediaElement(videoElement1);
            flvPlayer1.load();
            flvPlayer1.play();

            var videoElement2 = document.getElementById('videoElement2');
            videoElement2.muted = false; 
            var flvPlayer2 = flvjs.createPlayer({
                isLive: true,
                type: 'flv',
                url: '${videos.video2}'
            });
            flvPlayer2.attachMediaElement(videoElement2);
            flvPlayer2.load();
            flvPlayer2.play();

            var videoElement3 = document.getElementById('videoElement3');
            // videoElement3.muted = false; 
            var flvPlayer3 = flvjs.createPlayer({
                isLive: true,
                type: 'flv',
                url: '${videos.video3}'
            });
            flvPlayer3.attachMediaElement(videoElement3);
            flvPlayer3.load();
            flvPlayer3.play();

            var videoElement4 = document.getElementById('videoElement4');
            videoElement4.muted = false; 
            var flvPlayer4 = flvjs.createPlayer({
                isLive: true,
                type: 'flv',
                url: '${videos.video4}'
            });
            flvPlayer4.attachMediaElement(videoElement4);
            flvPlayer4.load();
            flvPlayer4.play();
        }else{
            alert("no suportes");
        }
    </script>






<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 			    <video width="750" height="350" -->
<!-- 			        id="my-player1" -->
<!-- 			        class="video-js" -->
<!-- 			        controls -->
<!-- 			        preload="auto"> -->
<%-- 			      <source src="${videos.video1}" type="video/x-flv"></source> --%>
<!-- 			      <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
<!-- 			      <p class="vjs-no-js"> -->
<!-- 			        To view this video please enable JavaScript, and consider upgrading to a -->
<!-- 			        web browser that -->
<!-- 			        <a href="http://videojs.com/html5-video-support/" target="_blank"> -->
<!-- 			          supports HTML5 video -->
<!-- 			        </a> -->
<!-- 			      </p> -->
<!-- 			    </video> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 			    <video width="750" height="350" -->
<!-- 			        id="my-player2" -->
<!-- 			        class="video-js" -->
<!-- 			        controls -->
<!-- 			        preload="auto"> -->
<%-- 			      <source src="${videos.video2}" type="video/x-flv"></source> --%>
<!-- 			      <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
<!-- 			      <p class="vjs-no-js"> -->
<!-- 			        To view this video please enable JavaScript, and consider upgrading to a -->
<!-- 			        web browser that -->
<!-- 			        <a href="http://videojs.com/html5-video-support/" target="_blank"> -->
<!-- 			          supports HTML5 video -->
<!-- 			        </a> -->
<!-- 			      </p> -->
<!-- 			    </video> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 			    <video width="750" height="350" -->
<!-- 			        id="my-player3" -->
<!-- 			        class="video-js" -->
<!-- 			        controls -->
<!-- 			        preload="auto"> -->
<%-- 			      <source src="${videos.video3}" type="video/x-flv"></source> --%>
<!-- 			      <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
<!-- 			      <p class="vjs-no-js"> -->
<!-- 			        To view this video please enable JavaScript, and consider upgrading to a -->
<!-- 			        web browser that -->
<!-- 			        <a href="http://videojs.com/html5-video-support/" target="_blank"> -->
<!-- 			          supports HTML5 video -->
<!-- 			        </a> -->
<!-- 			      </p> -->
<!-- 			    </video> -->
<!-- 			</td> -->
<!-- 			<td> -->
<!-- 			    <video width="750" height="350" -->
<!-- 			        id="my-player4" -->
<!-- 			        class="video-js" -->
<!-- 			        controls -->
<!-- 			        preload="auto"> -->
<%-- 			      <source src="${videos.video4}" type="video/x-flv"></source> --%>
<!-- 			      <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
<!-- 			      <p class="vjs-no-js"> -->
<!-- 			        To view this video please enable JavaScript, and consider upgrading to a -->
<!-- 			        web browser that -->
<!-- 			        <a href="http://videojs.com/html5-video-support/" target="_blank"> -->
<!-- 			          supports HTML5 video -->
<!-- 			        </a> -->
<!-- 			      </p> -->
<!-- 			    </video> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
    
<!--     <script type="application/javascript"> -->
//         var player = videojs('my-player1');
//         var player = videojs('my-player2');
//         var player = videojs('my-player3');
//         var player = videojs('my-player4');
<!--     </script> -->

</body>
</html>