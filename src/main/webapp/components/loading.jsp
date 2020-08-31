<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade in" ng-if="$root.loading">
	<div class="modal-dialog">
		<div class="loader"></div>
	</div>
</div>
<div class="modal-backdrop fade in" ng-if="$root.loading"></div>