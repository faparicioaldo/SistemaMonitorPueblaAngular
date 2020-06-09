package com.stv.quartzdemo.helper;

import com.stv.quartzdemo.dto.SemoviRequestDTO;
import com.stv.quartzdemo.dto.SemoviResponseDTO;

public interface ClienteSemoviPuebla {
	public SemoviResponseDTO enviarMensajeSemovi(SemoviRequestDTO datos );
}
