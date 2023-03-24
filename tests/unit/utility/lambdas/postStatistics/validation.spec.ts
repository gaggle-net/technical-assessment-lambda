import { ValidationPostStatistics } from '../../../../../src/lambdas/postStatistics/validation';

describe('Post Statistics Lambda Validation  Tests', () => {

    describe('checkForValidation() tests:', () => {
        it(`checks for request body without a numbers attribute`, () => {
            const errorMessage = ValidationPostStatistics.checkForValidation(undefined);
            expect(errorMessage).toBe(ValidationPostStatistics.REQUIRED_NUMBERS_ATTRIBUTE);
        });

        it(`checks if numbers attribute is an array`, () => {
            const errorMessage = ValidationPostStatistics.checkForValidation(`test`);
            expect(errorMessage).toBe(ValidationPostStatistics.EXPECTED_TO_BE_ARRAY);
        });

        it(`checks if all items in array is a number data type`, () => {
            const errorMessage = ValidationPostStatistics.checkForValidation([`test`, 1, 2, 3]);
            expect(errorMessage).toBe(ValidationPostStatistics.INCORRECT_DATA_TYPE_IN_ARRAY);
        });
    });
});