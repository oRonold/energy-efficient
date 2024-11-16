package com.fiap.gs.energyEfficient.services;

import com.fiap.gs.energyEfficient.model.morador.Morador;
import com.fiap.gs.energyEfficient.model.sensor.Medicao;
import com.fiap.gs.energyEfficient.model.sensor.Sensor;
import com.fiap.gs.energyEfficient.model.sensor.dto.*;
import com.fiap.gs.energyEfficient.repositories.MedicaoRepository;
import com.fiap.gs.energyEfficient.repositories.SensorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MedicaoRepository medicaoRepository;

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

    public Page<DetalhesSensorDTO> listarSensores(Pageable pageable) {
        return sensorRepository.findAll(pageable).map(DetalhesSensorDTO::new);
    }

    public Sensor atualizar(Long id, AtualizarSensorDTO dto){
        var sensor = sensorRepository.getReferenceById(id);
        sensor.atualizar(dto);
        return sensor;
    }

    public Page<ListarMedidasDTO> listarMedicoes(Long id, Pageable pageable){
        return medicaoRepository.procurarTodasMedidasPorSensor(id, pageable).map(ListarMedidasDTO::new);
    }

}
