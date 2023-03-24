import express from 'express';
import serverless from 'serverless-http';
import { StatisticUtil } from 'src/utility/statisticUtil';
import { ValidationPostStatistics } from './validation';

const app = express();


app.use(express.json({ limit: '10mb' }));
app.use(`/statistics`, async (req: express.Request, res: express.Response, next: express.NextFunction) => {
  try {
      
     
      const validationErrorMessage = ValidationPostStatistics.checkForValidation(req.body.numbers);
      if (validationErrorMessage)
      {
        return res.status(400).json({ message: validationErrorMessage });
      }

      
      return res.status(200).json({
        mean: StatisticUtil.getMean(req.body.numbers),
        median: StatisticUtil.getMedian(req.body.numbers),
        mode: StatisticUtil.getMode(req.body.numbers),
      });
    } catch (err) {
        return next(err);
    }
});


module.exports.handler = serverless(app);
