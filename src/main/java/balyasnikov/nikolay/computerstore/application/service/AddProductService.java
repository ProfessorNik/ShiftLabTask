package balyasnikov.nikolay.computerstore.application.service;

import balyasnikov.nikolay.computerstore.domain.entity.*;
import balyasnikov.nikolay.computerstore.infrastructure.dto.*;
import balyasnikov.nikolay.computerstore.infrastructure.repository.ProductRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddProductService {
    public ProductRepository repository;

    public HardDrive addHdd(HardDriveDto dto){
        var hdd = new HardDrive();
        hdd.setCapacity(dto.getCapacity());
        setMainParams(hdd, dto);
        return repository.save(hdd);
    }

    public DesktopComputer addDesktopComputer(DesktopComputerDto dto){
        var desktopComputer = new DesktopComputer();
        desktopComputer.setFormFactor(dto.getFormFactor());
        setMainParams(desktopComputer, dto);
        return repository.save(desktopComputer);
    }

    public Laptop addLaptop(LaptopDto dto){
        var laptop = new Laptop();
        laptop.setSize(dto.getSize());
        setMainParams(laptop, dto);
        return repository.save(laptop);
    }

    public Monitor addMonitor(MonitorDto dto){
        var monitor = new Monitor();
        monitor.setDiagonal(dto.getDiagonal());
        setMainParams(monitor, dto);
        return repository.save(monitor);
    }

    public void setMainParams(Product product, ProductDto productDto){
        product.setCost(productDto.getCost());
        product.setManufacturer(productDto.getManufacturer());
        product.setSeriesNumber(productDto.getSeriesNumber());
        product.setQuantity(productDto.getQuantity());
    }

}