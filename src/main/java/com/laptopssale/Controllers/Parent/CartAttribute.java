package com.laptopssale.Controllers.Parent;

import com.laptopssale.Entities.Processor;

import java.util.Set;

public class CartAttribute {
    private Set<Processor> processorsSet;

    public CartAttribute() {
    }

    public CartAttribute(Set<Processor> processors) {
        this.processorsSet = processors;
    }

    public Set<Processor> getProcessors() {
        return processorsSet;
    }

    public void setProcessors(Set<Processor> processors) {
        this.processorsSet = processors;
    }
}
