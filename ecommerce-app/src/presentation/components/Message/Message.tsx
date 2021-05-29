import { Modal, notification } from "antd";

type TMessage = {
  title: string
  content: string
}

export const showErrorModal = ({ title, content}: TMessage) => {
  Modal.error({
    title,
    content,
    okText: "confirmar"
  });
}

export const showSuccessNotification = ({ title, content}: TMessage) => {
  notification.success({
    message: title,
    description: content,
    placement: "topRight",
    style: {top: 70}
  })
}
