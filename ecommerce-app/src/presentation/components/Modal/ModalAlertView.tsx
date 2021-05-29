import { Modal, Button, Space } from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons';

type Props = {
    title: string,
    visible: boolean
    onOk: () => void 
    okText: string
    children: React.ReactNode
    icon?: React.ReactNode
}
const ModalAlertView: React.FC<Props> = ({ 
  title, 
  visible, 
  onOk, 
  okText, 
  children,
  icon
}) => {
  return (
    <Modal
          title={title}
          visible={visible}
          onOk={onOk}
          okText={okText}
        >
        {children}
    </Modal>
  );
};

export default ModalAlertView;
