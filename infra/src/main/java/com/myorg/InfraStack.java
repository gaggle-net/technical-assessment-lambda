package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Duration;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class InfraStack extends Stack {
    public InfraStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public InfraStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

    }
}
