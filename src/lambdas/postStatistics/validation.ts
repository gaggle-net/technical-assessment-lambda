


export class ValidationPostStatistics {
   static REQUIRED_NUMBERS_ATTRIBUTE = `Required 'numbers' attribute is missing from request body.`;
   static EXPECTED_TO_BE_ARRAY = `Expected numbers attribute to be an array.`;
   static INCORRECT_DATA_TYPE_IN_ARRAY = `Expected numbers array attribute to contain all number data types.`;

    static checkForValidation(numbers: any): string | undefined
    {
        if (!numbers)
      {
        return ValidationPostStatistics.REQUIRED_NUMBERS_ATTRIBUTE;
      }

      if (!Array.isArray(numbers))
      {
        return ValidationPostStatistics.EXPECTED_TO_BE_ARRAY;
      }

      

      if (ValidationPostStatistics.checkForDataTypesOtherThanNumber(numbers))
      {
        return ValidationPostStatistics.INCORRECT_DATA_TYPE_IN_ARRAY;
      }

      return undefined;
    }

    static checkForDataTypesOtherThanNumber(arrayOfNumbers: any): boolean
    {
        let somethingIsNotNumber = false;
        arrayOfNumbers.forEach(function(num: any){
          if(typeof num !== 'number'){
            somethingIsNotNumber = true;
          }
      })
      return somethingIsNotNumber;
    }

}