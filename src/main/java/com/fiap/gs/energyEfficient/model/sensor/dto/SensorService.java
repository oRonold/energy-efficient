package com.fiap.gs.energyEfficient.model.sensor.dto;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import com.fiap.gs.energyEfficient.repositories.MedicaoRepository;
import com.fiap.gs.energyEfficient.repositories.SensorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MedicaoRepository medicaoRepository;
    @Autowired
    private ServletWebServerFactoryAutoConfiguration servletWebServerFactoryAutoConfiguration;

    public Sensor cadastrar(CriarSensorDTO dto){
        var sensor = new Sensor(dto);
        var morador = (Morador) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sensor.setMorador(morador);
        morador.getSensores().add(sensor);

        return sensorRepository.save(sensor);
    }

    public Medicao criarMedida(Long idSensor, CriarMedidaDTO dto){
        if(!sensorRepository.existsById(idSensor)){
            throw new ValidationException("O sensor não existe");
        }
        var sensor = sensorRepository.getReferenceById(idSensor);
        var medicao = new Medicao(dto);

        sensor.getMedicoes().add(medicao);
        medicao.setSensor(sensor);

        var consumo = (dto.valorCorrente().doubleValue() * dto.valorTensao().doubleValue()) / 1000;
        medicao.setValorConsumo(BigDecimal.valueOf(consumo));

        medicaoRepository.save(medicao);
        return medicao;
    }

}
