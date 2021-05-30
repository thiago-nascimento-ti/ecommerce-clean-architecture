import { Modal, notification } from "antd";
import { styled } from "./MessageStyled";

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
    message: title,
    description: content,
    placement: "topRight",
    style: styled.notification
  })
}