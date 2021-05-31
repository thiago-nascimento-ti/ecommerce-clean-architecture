export const styled = {
  div: {
    display: "flex" as "flex",
    flexDirection: "row" as "row",
    justifyContent: "space-between" as "space-between"
  }
}

export const Styled = (color: string, fontSize: string, fontWeight: number = 500) => {
  return {
    div: styled.div,
    p: {
      color,
      fontSize,
      fontWeight
    }
  }
}