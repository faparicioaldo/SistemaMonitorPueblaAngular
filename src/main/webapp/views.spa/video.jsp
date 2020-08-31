<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>videojs play flv</title>
    <link href="http://vjs.zencdn.net/6.2.8/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/6.2.8/video.js"></script>
    <script src="https://unpkg.com/videojs-flash@2.0.1/dist/videojs-flash.js"></script>

<!--
    <link href="<c:url value = "/resources/video-js.css"/>" rel="stylesheet">
    <script src="<c:url value = "/resources/video.js"/>"></script>
    <script src="<c:url value = "/resources/videojs-flash.js"/>"></script>
-->
</head>
<body>

	<table>
		<tr>
			<td>
			    <video width="750" height="350"
			        id="my-player1"
			        class="video-js"
			        controls
			        preload="auto">
			      <source src="${videos.video1}" type="video/x-flv"></source>
			      <!-- <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
			      <p class="vjs-no-js">
			        To view this video please enable JavaScript, and consider upgrading to a
			        web browser that
			        <a href="http://videojs.com/html5-video-support/" target="_blank">
			          supports HTML5 video
			        </a>
			      </p>
			    </video>
			</td>
			<td>
			    <video width="750" height="350"
			        id="my-player2"
			        class="video-js"
			        controls
			        preload="auto">
			      <source src="${videos.video2}" type="video/x-flv"></source>
			      <!-- <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
			      <p class="vjs-no-js">
			        To view this video please enable JavaScript, and consider upgrading to a
			        web browser that
			        <a href="http://videojs.com/html5-video-support/" target="_blank">
			          supports HTML5 video
			        </a>
			      </p>
			    </video>
			</td>
		</tr>
		<tr>
			<td>
			    <video width="750" height="350"
			        id="my-player3"
			        class="video-js"
			        controls
			        preload="auto">
			      <source src="${videos.video3}" type="video/x-flv"></source>
			      <!-- <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
			      <p class="vjs-no-js">
			        To view this video please enable JavaScript, and consider upgrading to a
			        web browser that
			        <a href="http://videojs.com/html5-video-support/" target="_blank">
			          supports HTML5 video
			        </a>
			      </p>
			    </video>
			</td>
			<td>
			    <video width="750" height="350"
			        id="my-player4"
			        class="video-js"
			        controls
			        preload="auto">
			      <source src="${videos.video4}" type="video/x-flv"></source>
			      <!-- <source src="rtmp://192.168.3.234/testlive/game" type="rtmp/flv"></source> -->
			      <p class="vjs-no-js">
			        To view this video please enable JavaScript, and consider upgrading to a
			        web browser that
			        <a href="http://videojs.com/html5-video-support/" target="_blank">
			          supports HTML5 video
			        </a>
			      </p>
			    </video>
			</td>
		</tr>
	</table>
    
    <script type="application/javascript">
        var player = videojs('my-player1');
        var player = videojs('my-player2');
        var player = videojs('my-player3');
        var player = videojs('my-player4');
    </script>

</body>
</html>