

import { Statistic, Row, Col } from 'antd';
const { Countdown } = Statistic;

export default function Weather() {
  const { Countdown } = Statistic;
  const deadline = Date.now() + 1000 * 60 * 60 * 24 * 2 + 1000 * 30; // Moment is also OK
  return (
    <div>
      <Row gutter={5}>
        <Col span={24}>
          <Countdown title="Countdown" value={deadline} />
        </Col>
      </Row>,
    </div>
  );
}
