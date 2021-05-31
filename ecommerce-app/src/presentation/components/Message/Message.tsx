import { Modal, notification } from "antd";

type TMessage = {
  title: string
  content: string
}

export const showErrorModal = ({ title, content}: TMessage, onOk: () => void = () => {}) => {
  Modal.error({
    title,
    content,
    okText: "confirmar",
    afterClose: onOk
  });
}

export const showSuccessModal = ({ title, content}: TMessage, onOk: () => void = () => {}) => {
  Modal.success({
    title,
    content,
    okText: "confirmar",
    afterClose: onOk
  });
}

export const showSuccessNotification = ({ title, content}: TMessage) => {
  notification.success({
    top: 70,
    duration: 3.5,
    message: title,
    description: content,
    placement: "topRight",
  })
}