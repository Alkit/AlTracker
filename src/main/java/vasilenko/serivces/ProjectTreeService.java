package vasilenko.serivces;

import vasilenko.serivces.dto.TreeNode;

import java.util.List;

/**
 * Created by Alkit on 1/15/2017.
 */
public interface ProjectTreeService {
    public List<TreeNode> getProjectTree(int projectId);
}
