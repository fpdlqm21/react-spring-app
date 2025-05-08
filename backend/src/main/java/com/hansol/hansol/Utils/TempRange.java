package com.hansol.hansol.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TempRange {
    int min, max;
    String clothing;

    public boolean matches(int temp){
        return temp>=min && temp<=max;
    }
}
