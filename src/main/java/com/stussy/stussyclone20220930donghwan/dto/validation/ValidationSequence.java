package com.stussy.stussyclone20220930donghwan.dto.validation;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;


@GroupSequence({ValidationGroups.NotBlankGroup.class,
        ValidationGroups.SizeGroup.class,
        ValidationGroups.PatternCheckGroup.class,
        Default.class
})

public interface ValidationSequence {

}
