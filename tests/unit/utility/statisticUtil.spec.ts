import { StatisticUtil } from '../../../src/utility/statisticUtil';

describe('StatisticUtil.ts Tests', () => {

    describe('getMean() tests:', () => {
        it(`returns mean value`, () => {
        const meanValue = StatisticUtil.getMean([1,4,7]);
        expect(meanValue).toBe(4);
        });
    });

    describe('getMedian() tests:', () => {
        it(`returns median value`, () => {
            const medianValue = StatisticUtil.getMedian([1,4,7]);
            expect(medianValue).toBe(4);
        });

        it(`returns median value for even count in number array`, () => {
            const medianValue = StatisticUtil.getMedian([1,4,5,7]);
            expect(medianValue).toBe(4.5);
        });

        it(`returns median value for unsorted array`, () => {
            const medianValue = StatisticUtil.getMedian([12,32,1]);
            expect(medianValue).toBe(12);
        });

        it(`returns median value for large array`, () => {
            const medianValue = StatisticUtil.getMedian([12,32,1,2,2,3,43,5,123,9]);
            expect(medianValue).toBe(7);
        });
    });

    describe('getMode() tests:', () => {
        it(`returns mode value`, () => {
            const modeValue = StatisticUtil.getMode([1,4,4,7]);
            expect(modeValue).toBe(4);
        });

        it(`returns mode value for multiple numbers that have similar frequencies`, () => {
            const modeValue = StatisticUtil.getMode([7,4,4,5,5,8,7]);
            expect(modeValue).toBe(4);
        });
    });

});