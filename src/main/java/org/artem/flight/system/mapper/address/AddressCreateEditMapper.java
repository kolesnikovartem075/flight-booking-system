package org.artem.flight.system.mapper.address;

import org.artem.flight.system.database.entity.Address;
import org.artem.flight.system.dto.AddressCreateEditDto;
import org.artem.flight.system.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AddressCreateEditMapper implements Mapper<AddressCreateEditDto, Address> {


    public Address map(AddressCreateEditDto object) {
        var address = new Address();
        copy(object, address);

        return address;
    }

    public Address map(AddressCreateEditDto fromObject, Address toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(AddressCreateEditDto fromObject, Address target) {
        target.setName(fromObject.getName());
    }
}
