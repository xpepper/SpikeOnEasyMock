/*
 * Copyright (c) 2001-2007 OFFIS, Tammo Freese.
 * This program is made available under the terms of the MIT License.
 */
package org.easymock.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = { ArgumentsMatcherTest.class, ArrayMatcherTest.class, DefaultMatcherTest.class,
        EqualsMatcherTest.class, ExpectedMethodCallTest.class, LegacyBehaviorTests.class, MatchableArgumentsTest.class,
        InvocationTest.class, MockNameTest.class, NiceMockControlTest.class,
        NiceMockControlLongCompatibleReturnValueTest.class, ObjectMethodsTest.class,
        RecordStateInvalidDefaultReturnValueTest.class, RecordStateInvalidDefaultThrowableTest.class,
        RecordStateInvalidMatcherTest.class, RecordStateInvalidRangeTest.class,
        RecordStateInvalidReturnValueTest.class, RecordStateInvalidStateChangeTest.class,
        RecordStateInvalidThrowableTest.class, RecordStateInvalidUsageTest.class,
        RecordStateMethodCallMissingTest.class, ReplayStateInvalidCallsTest.class, ReplayStateInvalidUsageTest.class,
        StacktraceTest.class, UsageCallCountTest.class, UsageDefaultReturnValueTest.class,
        UsageExpectAndDefaultReturnTest.class, UsageExpectAndDefaultThrowTest.class, UsageExpectAndReturnTest.class,
        UsageExpectAndThrowTest.class, UsageFloatingPointReturnValueTest.class,
        UsageLongCompatibleReturnValueTest.class, UsageOverloadedDefaultValueTest.class,
        UsageOverloadedMethodTest.class, UsageUnorderedTest.class, UsageRangeTest.class, UsageStrictMockTest.class,
        UsageTest.class, UsageThrowableTest.class, UsageVarargTest.class, UsageVerifyTest.class,
        org.easymock.tests2.UsageStrictMockTest.class, org.easymock.tests2.UsageTest.class,
        org.easymock.tests2.StubTest.class, org.easymock.tests2.UsageMatchersTest.class,
        org.easymock.tests2.NiceMockTest.class, org.easymock.tests2.ConstraintsToStringTest.class,
        org.easymock.tests2.CallbackTest.class, org.easymock.tests2.CallbackAndArgumentsTest.class,
        org.easymock.tests2.UsageConstraintsTest.class, org.easymock.tests2.AnswerTest.class,
        org.easymock.tests2.NameTest.class, org.easymock.tests2.CompareToTest.class })
public class AllTests {
}
