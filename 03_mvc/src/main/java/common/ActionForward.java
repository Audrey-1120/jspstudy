package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // 이 안에 getter, setter 모두 들어있음. 빌더 패턴은 안들어가있음.
@Builder
public class ActionForward {
  private String view;
  private boolean isRedirect;

}
