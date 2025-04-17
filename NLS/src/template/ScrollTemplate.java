package template;

import interfaces.Readable;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.*;
import vo.BaseVO;

/**
 * input: BaseDAO를 상속받는 DAO output: JPanel(스크롤 가능)을 리턴하는데, 그 안에 DAO로 가져온 VO를 하나의
 * 행으로 가짐.
 *
 */
class ScrollTemplate extends JPanel {

    private Readable<BaseVO> DAO;

    public JListStudy(Readable<BaseVO> DAO) {
        this.DAO = DAO;

// 2. DAO를 통해 데이터베이스에서 모든 아이템을 가져옴
        List<BaseVO> items = DAO.read();
        // List 인터페이스 중에서 ArrayList에 담아서 옴

// 3. JList에 데이터를 표시하기 위한 모델 생성
        DefaultListModel<BaseVO> model = new DefaultListModel<>();

// 4. 가져온 아이템들을 모델에 추가
        for (BaseVO item : items) {
            model.addElement(item);
        }

// 5. JList 생성 및 모델 설정
        JList<BaseVO> list = new JList<>(model);
        list.setLayoutOrientation(JList.VERTICAL);  // 세로로 나열

// 커스텀 렌더러 설정
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                Field[] fields = value.getClass().getDeclaredFields();
                JPanel panel = new JPanel(new GridLayout(1, fields.length, 5, 0));

                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        Object fieldValue = field.get(value);

                        if (fieldValue != null) {
                            JLabel label = new JLabel(fieldValue.toString());
                            label.setHorizontalAlignment(JLabel.CENTER);
                            panel.add(label);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                return panel;
            }
        });

// JList를 JScrollPane으로 감싸기
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(400, 300));  // 원하는 크기 설정

// 프레임에 추가
        this.add(scrollPane);
    }
}
