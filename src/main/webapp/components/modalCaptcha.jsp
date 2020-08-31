<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- ventana captcha modal -->
<form name="captchaForm" autocomplete="off" ng-submit="submit()">
	<div class="modal fade" id="captchaModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Captcha</h4>
				</div>
				<div class="modal-body">
					<div>
						<table class="table table-bordered">
							<tr>
								<td>
									<div class="row">
										<div class="col-md-4">&nbsp;</div>
										<div class="col-md-4">
											<img id="imagenCaptcha" />
										</div>
										<div class="col-md-4">
											<input type="button" ng-click="$root.consultarOtroCaptcha()"
												class="btn btn-default" value="Intentar otro" />
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<label class="control-label" for="email-03"
												style="text-align: left"> C&oacute;digo de
												verificaci&oacute;n * </label>
										</div>
										<div class="col-md-4">
											<input ng-model="$ctrl.captcha" class="ns_ form-control"
												maxlength="13" size="15" required />
										</div>
										<div class="col-md-4" style="margin-top: 7px;">Escribir
											ambas palabras separadas por un espacio</div>
									</div>

									<div class="row">
										<div class="col-md-4">&nbsp;</div>
										<div class="col-md-4"></div>
										<div class="col-md-4">&nbsp;</div>
									</div>
								</td>
							</tr>
						</table>

						<div class="form-group">
							<div class="col-md-5 pull-left  text-vertical-align-button">*Campos
								obligatorios</div>

							<div class="col-md-7 " align="right">
								<!-- <input type="submit" class="btn btn-primary" value="Continuar" name="siguiente"></input> -->
								<span class="d-inline-block" aria-hidden="true"
									data-toggle="tooltip" data-placement="top" tabindex="0"
									title="{{ disableSubmit() ? 'Debe de ingresar el captcha para poder continuar con la consulta.' : ''}}">
									<input class="btn btn-primary" type="submit"
									style="pointer-events: {{ disableSubmit() ? 'none' : 'default'}};"
									ng-disabled="disableSubmit()" id="buttonEnviaPeticion"
									value="   Aceptar   " />
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
</form>