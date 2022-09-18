package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.domain.entity.*;
import balyasnikov.nikolay.computerstore.infrastructure.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetProductService {
    public ProductRepository productRepository;
    public DesktopComputerRepository desktopComputerRepository;
    public HardDriveRepository hardDriveRepository;
    public LaptopRepository laptopRepository;
    public MonitorRepository monitorRepository;

    public List<DesktopComputer> getDesktopComputers(){
        return desktopComputerRepository.findAll();
    }

    public List<HardDrive> getHardDrives(){
        return hardDriveRepository.findAll();
    }

    public List<Laptop> getLaptops(){
        return laptopRepository.findAll();
    }

    public List<Monitor> getMonitors(){
        return monitorRepository.findAll();
    }

    public Product getProductBy(Long id){
        return productRepository.findById(id).orElseThrow();
    }

}
