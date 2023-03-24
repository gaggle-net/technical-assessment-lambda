

export class StatisticUtil {

    static getMean(numbers: number[]): number {
        const sum = numbers.reduce((partialSum, a) => partialSum + a, 0);
        return sum / numbers.length;
    }

    static getMedian(numbers: number[]): number {
        const sortedNumbers = numbers.sort((num1, num2) => num1 - num2 );
        if (sortedNumbers.length % 2 === 0) {
            const midIndex = sortedNumbers.length / 2;
            return (sortedNumbers[midIndex - 1] + sortedNumbers[midIndex]) / 2;
        }
        return sortedNumbers[Math.floor(sortedNumbers.length / 2)];
    }

    static getMode(numbers: number[]): number {
        let frequencyOfNumbers: { [key:number]:number } = {};
        let greatestFrequency = 0;
        let mode = 0;

        numbers.forEach(num => {
            if (!frequencyOfNumbers[num])
                {
                    frequencyOfNumbers[num] = 1;
                }
            else
                {
                    frequencyOfNumbers[num]++;
                }
            })

        for (let num in frequencyOfNumbers) {
            if (frequencyOfNumbers[num] > greatestFrequency) {
                mode = +num;
                greatestFrequency = frequencyOfNumbers[num];
            }
        }

        return mode;
    }
}